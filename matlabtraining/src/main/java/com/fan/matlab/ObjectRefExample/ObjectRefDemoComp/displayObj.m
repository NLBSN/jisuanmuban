function className = displayObj(h)
%DISPLAYOBJ Display information about a Java object
%   This file is used as an example for the 
%   MATLAB Java Package product.

%   Copyright 2001-2007 The MathWorks, Inc.

h
className = class(h)
whos('h')
methods(h)