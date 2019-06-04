function [Q,R] = qrdecomp(A)
%QRDECOMP QR factorization of A.
%   [Q,R] = QRDECOMP(A) returns the QR factorization of A.
%   This file is used as an example for the MATLAB 
%   Java Package product.

%   Copyright 2001-2007 The MathWorks, Inc.

[Q,R] = qr(A);
