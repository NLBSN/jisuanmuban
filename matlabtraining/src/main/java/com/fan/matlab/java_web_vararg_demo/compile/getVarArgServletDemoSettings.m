function params = getVarArgServletDemoSettings
%
% This function sets the build options for the Rankine Java demo.
%

% Authored By: Elwin Chan
% Modified By: David Forstot

% Copyright 2006-2007 The MathWorks, Inc.


%% Matlab-related Java settings
% MATLAB program files to put in jar archive
params.mfileSourceRoot = fullfile(fileparts(pwd()), 'mcode');
params.mfileList = fullfile(params.mfileSourceRoot, 'varargexample.m');
% component name for jar
params.MLGeneratedComponentName = 'vararg_java';
% class name to generate
params.MLGeneratedClassName = 'vararg_javaclass';
%where to put the generated .jar and .ctf
params.MLOutputRoot = fullfile(params.mfileSourceRoot, params.MLGeneratedComponentName);


%% settings for external Java code
params.demoJavaCodeRoot = fullfile(fileparts(pwd()), 'JavaCode');
%where the java source files are
params.demoJavaSourceRoot = fullfile(params.demoJavaCodeRoot, 'src');
%where the additional libs are
params.demoJavaLibRoot = fullfile(params.demoJavaCodeRoot, 'lib');
% build root
params.buildRoot = fullfile(params.demoJavaCodeRoot, 'build');
% where to put the compiled Java code
params.compiledJavaPath = fullfile(params.buildRoot, 'WEB-INF', 'classes'); 
%where to put the generated .jar and .ctf
params.libOutput = fullfile(params.buildRoot, 'WEB-INF', 'lib'); 
% package name for the source code
params.demoJavaPackagePath = fullfile(params.demoJavaSourceRoot, ...
    'VarArg');

% additional jar name
params.demoJavaAdditionalJar1 = 'servlet-api.jar';

%% settings for output 
%where the properties files are
params.PropertiesLocation = params.demoJavaSourceRoot;
% the final jar filename
params.FinalJarName = 'VarArgServlet.war';
% where to put the final jar
params.FinalOutputRoot = fullfile(fileparts(pwd()), 'distrib');
