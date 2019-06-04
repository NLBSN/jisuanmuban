function [fftdata, freq, powerspect] = plotfft(data, interval)
%PLOTFFT Computes and plots the FFT and power spectral density.
%   [FFTDATA, FREQ, POWERSPECT] = PLOTFFT(DATA, INTERVAL)
%   Computes the FFT and power spectral density of the input data.
%   This file is used as an example for the MATLAB 
%   Java Package product.

%   Copyright 2001-2007 The MathWorks, Inc.

[fftdata, freq, powerspect] = computefft(data, interval);
len = length(fftdata);
if (len <= 0)
   return;
end

t = 0:interval:(len-1)*interval;
subplot(2,1,1), plot(t, data)
xlabel('Time'), grid on
title('Time domain signal')
subplot(2,1,2), plot(freq(1:len/2), powerspect(1:len/2))
xlabel('Frequency (Hz)'), grid on
title('Power spectral density')
