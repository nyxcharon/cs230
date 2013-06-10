%%%%%%%%%%%%%%%%
%Barry Martin
%1/21/2013
%Engr 102
%Problem 1.33
%%%%%%%%%%%%%%%%

clc
clear all

P=85000;
r=0.0575;
y=15;

num=P*(r/12);
div=1-(1+r/12)^(-12*y);
total=num/div

%----------
% total =
% 705.8486
%----------