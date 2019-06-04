function [fftdata, freq, powerspect] = computefft(data, interval)
%COMPUTEFFT Computes the FFT and power spectral density.
%   [FFTDATA, FREQ, POWERSPECT] = COMPUTEFFT(DATA, INTERVAL)
%   Computes the FFT and power spectral density of the input data.
%   This file is used as an example for the MATLAB 
%   Java Package product.

%   Copyright 2001-2007 The MathWorks, Inc.

if (isempty(data))
   fftdata = [];
   freq = [];
   powerspect = [];
   return;
end

if (interval <= 0)
   error('BuilderJA:Examples:samplingInterval','Sampling interval must be greater then zero');
   return;
end

fftdata = fft(data);
freq = (0:length(fftdata)-1)/(length(fftdata)*interval);
powerspect = abs(fftdata)/(sqrt(length(fftdata)));