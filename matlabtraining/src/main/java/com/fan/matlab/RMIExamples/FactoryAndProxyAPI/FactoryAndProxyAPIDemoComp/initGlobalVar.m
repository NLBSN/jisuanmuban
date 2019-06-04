function initGlobalVar(in)

global var1;

if isempty(var1)
    fprintf('\n**** Global variable is empty. Current value is : %s ****\n','[]');
    var1 = in;
else
    fprintf('\n**** Global variable already has a value. Current value is : %d ****\n',var1);
end
