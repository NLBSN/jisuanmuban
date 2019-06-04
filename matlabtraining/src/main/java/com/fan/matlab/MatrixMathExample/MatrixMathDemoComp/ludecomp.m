function [L,U] = ludecomp(A)
%LUDECOMP LU factorization of A.
%   [L,U] = LUDECOMP(A) returns the LU factorization of A.
%   This file is used as an example for the MATLAB 
%   Java Package product.

%   Copyright 2001-2007 The MathWorks, Inc.

[L,U] = lu(A);
