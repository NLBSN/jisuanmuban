function [x,fval] = doOptim(h, x0)
%DOOPTIM Optimize a Java objective function
%   This file is used as an example for the 
%   MATLAB Java Package product.

%   Copyright 2001-2007 The MathWorks, Inc.

% FMINSEARCH can't operate directly on Java objective functions, so
% create an anonymous function with the correct signature to wrap the Java
% object
% Here, we assume our object has a method evaluateFunction() that takes an 
% array of doubles and returns a double.  This could become an Interface, 
% and we could check that the object implements that Interface.
mWrapper = @(x) h.evaluateFunction(x);

% Compare two ways of evaluating the objective function
% These eventually call the same Java method, and return the same results
directEval = h.evaluateFunction(x0)
wrapperEval = mWrapper(x0)

[x,fval] = fminsearch(mWrapper,x0)
