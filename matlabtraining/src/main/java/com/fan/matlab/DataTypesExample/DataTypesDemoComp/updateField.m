function FinalStruct = updateField(st,field_name)

fprintf('\nEVENT 3: Partially initialized structure as received by MATLAB:\n\n');
disp(st);
fprintf('Address field as initialized from the client:\n\n');
disp(st.Address);
fprintf('##################################\n');

fprintf(['\nEVENT 4: Updating ''', field_name, ''' field before sending the structure back to the JAVA client:\n\n']);
st.(field_name) = 'MathWorks';
FinalStruct = st;
disp(FinalStruct);
fprintf('\n##################################\n');
