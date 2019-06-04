function compileVarArgServletDemo
%
% This function will compile the Rankine Java demo.
%

% Authored By: Elwin Chan
% Modified By: David Forstot

% Copyright 2007 The MathWorks, Inc.

% get the settings
params = getVarArgServletDemoSettings;

% Do the compiling
setPathForJava;
doMcc(params);
doJavac(params);
buildJar(params);

% Display Done Message!
disp([sprintf('\nRun this demo by copying this file: \n\t %s', ...
    fullfile(params.FinalOutputRoot, params.FinalJarName)), ...
    sprintf('\ninto your web servers webapp directory.\n')]);


%%
function setPathForJava
% Ensure that the system path has been set up for Java compilation.

%see if JAVA_HOME has been set as an environment variable.  If not set it.
jdkDirName = getenv('JAVA_HOME');
if(isempty(jdkDirName))
    % get the JDK location from the user
    jdkDirName = uigetdir(matlabroot, 'Please select JDK Location:');

    if(jdkDirName == 0)
        error('Cannot compile PortOptDemo without the JDK installed');
    end

    %strip off '\bin' if it is there
    if(strcmpi(jdkDirName(end-3:end), '\bin'))
        jdkDirName = jdkDirName(1:end-4);
    end

    %set the JAVA_HOME environment variable
    disp('Setting the JAVA_HOME environment variable');
    setenv('JAVA_HOME', jdkDirName);
end

% see if JAVA_HOME\bin is on the path - if not, add it.
syspath = getenv('PATH');
if isempty(strfind(lower(syspath), lower(fullfile(jdkDirName, 'bin'))))
    disp('Adding JAVA_HOME\bin to the system path')
    setenv('PATH', [syspath, ';' fullfile(jdkDirName, 'bin')]);
end

%%
function doMcc(buildParams)
% Use Builder for Java to compile up the Matlab code into a jar

%check if the output directory exists
if(exist(buildParams.MLOutputRoot, 'dir') ~= 7)
    mkdir(buildParams.MLOutputRoot);
end

% generate the mcc command
mcccmd     = ['mcc -v -d "' buildParams.MLOutputRoot, '" ' ...
    '-W "java:' buildParams.MLGeneratedComponentName ',' ...
    buildParams.MLGeneratedClassName '" "' buildParams.mfileList '"'];

% Do the mcc to compile the Matlab code into a jar
disp('Compiling Matlab Code...');
[status mccout] = system(mcccmd);

if ( (status ~= 0) || (exist(fullfile(buildParams.MLOutputRoot, ...
        [buildParams.MLGeneratedComponentName '.jar']),'file') ~= 2) )
    error('System and MCC command:\n%s\n\nOutput of the command:%s\n\n',mcccmd,mccout);
else
    disp(sprintf('\tDone'));
end

%%
function doJavac(buildParams)
% Compile the java code

%check if the output directory exists
if(exist(buildParams.compiledJavaPath, 'dir') ~=7)
    mkdir(buildParams.compiledJavaPath);
end

classStr = { '.' pathsep, ...
    '"', fullfile(matlabroot,'toolbox','javabuilder','jar','javabuilder.jar'), '"', pathsep, ...
    '"', fullfile(buildParams.MLOutputRoot, [buildParams.MLGeneratedComponentName '.jar']), '"', pathsep, ...
    '"', fullfile(matlabroot,'java','jarext','j2ee',buildParams.demoJavaAdditionalJar1), '"'};

classpath = strcat(classStr{:});

% Don't put quotes around the file list because javac doesn't like it.
javaCodeFileList = fullfile(buildParams.demoJavaPackagePath, 'VarArgServletClass.java');

% form the javac command for compiling the code
javaccmd = ['javac -classpath ' classpath , ' -d "', buildParams.compiledJavaPath, '" "', javaCodeFileList, '"'];

% do the javac command
disp('Compiling Java Code...');
[status javacout] = system(javaccmd);

if ( (status~=0))
    error('JAVAC command used to compile the client application :\n%s\n\nOutput of javac command :\n%s\n',javaccmd,javacout);
else
    disp(sprintf('\tDone'));
end

%%
function buildJar(buildParams)
%Build the jar

%check if the output directory exists
if(exist(buildParams.FinalOutputRoot, 'dir') ~=7)
    mkdir(buildParams.FinalOutputRoot);
end

%check if the lib directory exists
if(exist(buildParams.libOutput, 'dir') ~=7)
    mkdir(buildParams.libOutput);
end
%first copy javabuilder.jar and the MATLAB-generated .jar and .ctf into the
%final output folder so that everything can be bundled into a final jar
jarFilename = [buildParams.MLGeneratedComponentName '.jar'];

copyfile(fullfile(buildParams.MLOutputRoot, jarFilename), ...
         fullfile(buildParams.libOutput, jarFilename));

% form the jar command
jarcmd = ['jar cf ', '"', ...
    fullfile(buildParams.FinalOutputRoot, buildParams.FinalJarName), '"', ...
    ' -C ', '"', buildParams.buildRoot, '" .'];

% do the jar command
disp('Creating WAR...');
[status, jarout] = system(jarcmd);

if( (status ~= 0) || (exist(fullfile(buildParams.FinalOutputRoot, buildParams.FinalJarName), 'file') ~= 2))
    error('JAR command:\n%s\n\nOutput of the command:%s\n\n', jarcmd, jarout);
else
    disp(sprintf('\tDone'));
end
