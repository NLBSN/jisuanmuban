function PartialStruct = createEmptyStruct(field_names)

fprintf('EVENT 1: Initializing the structure in MATLAB and sending it to JAVA client:\n');

PartialStruct = struct();

for i=1:length(field_names)
    PartialStruct.(field_names{i}) = [];    
end

fprintf('         Initialized empty structure:\n\n');
disp(PartialStruct);
fprintf('\n##################################\n');



