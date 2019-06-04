function [w_fig, varargout] = varargexample(Data, varargin)
% VARARGEXAMPLE used varargin and varargout for demonstration purposes
%
%   The purpose of VARARGEXAMPLE is to demonstrate integration of MATLAB
%   created components using varargin and varargout. It also demonstrates
%   the use of webfigures for embedding MATLAB graphics in web pages.
%
%   The function VARARGEXAMPLE takes one required input, the data to plot.
%   The function then takes a variable number of token value pairs.  These
%   pairs set properties of the figure and plot to be created.
%
%   The function VARARGEXAMPLE returns a variable number of arguments.
%   When no arguments are specified, none are returned.  For a single 
%   argument, the mean of the data to be plotted is returned.  For two
%   arguments, the mean and standard deviation of the data to be plotted
%   is returned.
%
%   Syntax:
%       varargexample(Data)
%       varargexample(Data, PropertyName, PropertyValue, ...)
%
%       mean = varargexample(Data, ...)
%       [mean, std] = varargexample(Data, ...)
%
%   Example: 
%       % to produce a simple line plot from 1:10
%       varargexample(1:10)
%
%       % to produce a green line from 1:10 with red border
%       varargexample(1:10, 'LineColor', 'green', 'BorderColor', 'red')
%
%       % to get the mean of a red line from 1:10
%       datamean = varargexample(1:10, 'LineColor', 'red')
%
%       % to get the mean and standard deviation of a blue line from 1:10
%       [datamean, datadev] = varargexample(1:10, 'LineColor', 'blue')
%

% David Forstot
% Copyright 2006-2007 The MathWorks, Inc.
% This code is provided for example only.

% Verify Data was provided
if (nargin < 1) 
    error('At least one argument must be provided');
end

% Verify data is both numeric and real
if ~isnumeric(Data) || ~isreal(Data)
    error('First argument should be the real numeric data to be plotted');
end

% Verify if optional arguments were provided that the
% number of input arguments is odd.  Since each token
% requires a value, that is they are a pair we also
% have Data, a required input, that means the total
% number of arguments is an even multiple plus one, odd.
if ( (nargin > 1) && ( mod(nargin,2) ~= 1) )
        error('Unexpected calling syntax, ');
end

% Since we have at least one argument, we plot the data
% for this example the figure is not displayed, but the handle
% will be used later!
h_fig = figure('visible','off', 'Menubar', 'none', ...
        'PaperPositionMode','auto', 'Numbertitle', 'off', ...
        'Name', 'VarArg Example');
set(h_fig,'Visible','off');
h_plot = plot(Data);


% In case we have more than one argument,
% we need to process the token-value pairs
%
% To do this we will use a for loop that starts at
% three and increments pairwise (by twos) to the
% total number of arguments.
%
% When three is greater than nargin,
% this will not execute

for argcount = 3:2:nargin
	% we have token value pairs, pass each pair
    % to the parsing function nested below
    parse_args(varargin{argcount-2}, varargin{argcount-1});
end
        
% Output varies based on number of
% output arguments expected
switch nargout
    case {0,1}
        % return nothing
        varargout = {};
    case 2
        % return only the mean
        varargout{1} = mean(Data);
    case 3
        % return first the mean, then the standard deviation
        varargout{1} = mean(Data);
        varargout{2} = std(Data);
end % end switch nargout

    % This is a nested function to parse the property pairs
    %
    % It uses the variables h_plot and h_fig from the parent
    % function
    function parse_args( token, value )
        switch lower(token)
            case 'linecolor'
                set(h_plot, 'Color', value);
            case 'bordercolor'
                set(h_fig, 'Color', value);
            otherwise
                warning('VARARGEXAMPLE:Parameter', ...
                        'Unexpected property name in function call.');
        end % switch token
    end % function parse_args

w_fig = webfigure(h_fig);
close(h_fig);

end % VARARGEXAMPLE
