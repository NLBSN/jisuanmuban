function cleanUpVarArgServletDemo
% Cleans all output files from compileVarArgServletDemo.m and reduces the
% demo back to the original .m and java files.

% Authored By: Elwin Chan
% Modified By: David Forstot

% Copyright 2006-2007 The MathWorks, Inc.

params = getVarArgServletDemoSettings;

%delete the output directory for the mcc command 
if(exist(params.MLOutputRoot, 'dir') == 7)
    rmdir(params.MLOutputRoot, 's');
end

%delete the output directory for the javac command
if(exist(params.compiledJavaPath, 'dir') ==7)
    rmdir(params.compiledJavaPath, 's');
end

%delete the output directory for the lib files
if(exist(params.libOutput, 'dir') ~=7)
    rmdir(params.libOutput, 's');
end

%delete the output directory for the jar
if(exist(params.FinalOutputRoot, 'dir') ==7)
    rmdir(params.FinalOutputRoot, 's');
end
