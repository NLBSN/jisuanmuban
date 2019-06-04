function PartialStruct = createEmptyStruct(field_names)

fprintf('EVENT 1: Initializing the structure on server and sending it to client:\n');

PartialStruct = struct(field_names{1},' ',field_names{2},[]);

fprintf('         Initialized empty structure:\n\n');
disp(PartialStruct);
fprintf('\n##################################\n');



