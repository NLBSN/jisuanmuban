package com.fan.matlab.java_web_vararg_demo.JavaCode.src.VarArg;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
// Necessary package imports for using ML component
import com.mathworks.toolbox.javabuilder.*;
import com.mathworks.toolbox.javabuilder.webfigures.*;
import com.mathworks.toolbox.javabuilder.web.MWHttpSessionBinder;
// ml created component
import vararg_java.*;

public class VarArgServletClass extends HttpServlet
{
    vararg_javaclass fComponentInstance = null;

    public void init() throws ServletException
    {
        if(fComponentInstance == null)
        {
            try
            {
                fComponentInstance = new vararg_javaclass();
            }catch (Throwable t)
            {
                t.printStackTrace();
            }
        }
    }

    public void destroy()
    {
        if(fComponentInstance != null)
            fComponentInstance.dispose();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        Object[] outputArray = null;
        FormData formData    = extractDatafromForm(request);
        WebFigure wb;

        if ( !formData.valid() )
        {
            printHTMLError(response);
            return;
        }

        try
        {
            outputArray = fComponentInstance.varargexample(formData.getVarOutputs(),
                                               formData.getDataArray(), formData.getVarInputs() );
            wb          = (WebFigure)MWJavaObjectRef.unwrapJavaObjectRefs(outputArray[0]);

            // Set the figure scope to session
            request.getSession().setAttribute("Vararg_Figure",wb);
            // Bind the figure's lifetime to session
            request.getSession().setAttribute("Vararg_Figure_Binder", new MWHttpSessionBinder(wb));

            printHTMLOutput(request,response,wb,outputArray);

            // You can use the viewer (view.jsp) by uncommenting the following code and commenting the call to
            // printHTMLOutput(request,response).

           // updateSession(request.getSession(),outputArray);
           // RequestDispatcher dispatcher = request.getRequestDispatcher("/view.jsp");
           // dispatcher.forward(request, response);
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }
        finally
        {
            MWArray.disposeArray(outputArray);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        doGet(request, response);
    }

    private FormData extractDatafromForm(HttpServletRequest request)
    {
        Enumeration paramNames = request.getParameterNames();

        // declare return value
        FormData retVal = new FormData();

        // reset counts of input and output
        int inputCount = 0;

        // tmp variables for optional inputs
        String lineColor = null, borderColor = null, defaultColor = "Default";

        while(paramNames.hasMoreElements())
        {
            String paramName  = (String)paramNames.nextElement();
            String paramValue = request.getParameter(paramName);
            paramName         = paramName.toLowerCase();

            if((paramName.compareTo("linecolor") == 0) &&
                    (defaultColor.compareTo(paramValue) != 0))
            {
                lineColor = paramValue;
                inputCount++;

            } else if((paramName.compareTo("bordercolor") == 0) &&
                    (defaultColor.compareTo(paramValue) != 0))
            {

                borderColor = paramValue;
                inputCount++;

            } else if (paramName.compareTo("optoutput") == 0)
            {
                int outputCount = 1;

                if(paramValue.compareTo("Mean") == 0 )
                {
                    outputCount = 2;
                } else if (paramValue.compareTo("StdDev") == 0)
                {
                    outputCount = 3;
                }

                retVal.setVarOutputs(outputCount);

            } else if (paramName.compareTo("data") == 0)
            {
                String [] dataAtoms = paramValue.trim().split("\\s+");

                int numAtoms = dataAtoms.length;

                Double [] DataArray = new Double[numAtoms];

                for (int i = 0; i < numAtoms; i++)
                {
                    DataArray[i] = Double.valueOf( dataAtoms[i] );
                }

                retVal.setDataArray(DataArray);

            }// end if paramName
        } // end while

        if (inputCount > 0)
        {
            // each optional input takes two slots
            Object [] varArgArray = new Object[inputCount * 2];

            int incount = 0;

            // assign LineColor if necessary
            if (lineColor != null)
            {
                varArgArray[incount] = "LineColor";
                varArgArray[incount+1] = lineColor;

                incount+=2;
            }

            // assign BorderColor if necessary
            if (borderColor != null)
            {
                varArgArray[incount] = "BorderColor";
                varArgArray[incount + 1] = borderColor;
            }

            retVal.setVarInputs(varArgArray);
        }

        return retVal;
    }

    private void printHTMLOutput(HttpServletRequest request,
    							 HttpServletResponse response,
                                 WebFigure wb, Object[] outputArray)
    {
        response.setContentType("text/html;charset=UTF-8");

        WebFigureHtmlGenerator webFigures = new WebFigureHtmlGenerator(request);

        int outputCount = outputArray.length - 1;

        try
        {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                        "Transitional//EN\">\n");

            out.println("<html>");
            out.println("<head>");
            out.println("<title>VarArg Java Servlet Example</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<H1 align=\"center\">VarArg Java Servlet</H1><BR><BR>");

            // display the web figure
            out.println("<div ALIGN=\"CENTER\">");
            out.println(webFigures.getFigureEmbedString(wb,"Vararg_Figure", "session", "", "", ""));
            out.println("</div>");

            // print optional output if necessary
            if (outputCount > 0)
            {
                out.println("<BR><BR><table align=\"center\" border=\"2\">");
                out.println("<tr><td>Mean: </td><td>");
                out.println(String.valueOf(((MWNumericArray) outputArray[1]).getDouble(1)));
                out.println("</td></tr>");

                if(outputCount > 1)
                {
                    out.println("<tr><td>Std Dev: </td><td>");
                    out.println(String.valueOf(((MWNumericArray) outputArray[2]).getDouble(1)));
                    out.println("</td></tr>");
                }
                out.println("</table>");
            }

            out.println("</body>");
            out.println("</html>");
            out.close();
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }
    }

    private void printHTMLError(HttpServletResponse response)
    {
        try
        {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
                        "Transitional//EN\">\n");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>VarArg Java Servlet Example</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<H1><font=\"red\">No Data To Plot!</font></H1>");
            out.println("</body>");
            out.println("</html>");
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }
    }

    public void updateSession(HttpSession session, Object[] outputArray)
    {
        int outputCount = outputArray.length - 1;
        session.setAttribute("numOutputs", outputCount);

        if(outputCount > 0)
            session.setAttribute("mean", ((MWNumericArray) outputArray[1]).getDouble(1));

        if(outputCount > 1)
            session.setAttribute("stdDev", ((MWNumericArray) outputArray[2]).getDouble(1));
    }

    /**
     * The bean class to hold the Form Data.
     *
     * A bean class is a lightweight class that implements
     * getters and setters for its data compenents.
     * It simplifies code by encapulsating the data and
     * keeping all query-centric items in a single class.
     *
     */
    private class FormData
    {
        Double[] dataArray = null;
        Object[] varInputs = new Object[0];
        int varOutputs = 0;

        void setVarOutputs(int inVarOutputs)
        {
            varOutputs = inVarOutputs;
        }

        int getVarOutputs()
        {
            return varOutputs;
        }

        void setDataArray(Double[] inData)
        {
            dataArray = inData;
        }

        Double[] getDataArray()
        {
            return dataArray;
        }

        void setVarInputs(Object [] inVarInputs)
        {
            varInputs = inVarInputs;
        }

        Object [] getVarInputs()
        {
            return varInputs;
        }

        int getVarArgCount()
        {
            int retVal = 0;

            if (varInputs != null)
            {
                retVal = varInputs.length;
            }

            return retVal;
        }

        boolean valid()
        {
            return (dataArray != null && dataArray.length != 0);
        }
    }
 }
