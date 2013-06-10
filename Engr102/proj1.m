%%%%%%%%%%%%%%%%%%%%%
% Barry Martin
% ENGR 102 Project 1
% Proj.m
%%%%%%%%%%%%%%%%%%%%%

clear all
clc

%---Part A---------------------------------------------
BeamCost = 18;
Months = 12;
ROIUnits=200000;
PP_Units=100000;

RefurbTooling    =  70000;
StampedTooling   = 170000;
BlowTooling      = 200000;
InjectionTooling = 300000;
CompositeTooling =  18000;

RefurbPrice    = 17.65;
StampedPrice   = 17.16;
BlowPrice      = 16.54;
InjectionPrice = 15.81;
CompositePrice = 17.92;

RefurbROI = ((BeamCost-RefurbPrice)*ROIUnits-RefurbTooling)/RefurbTooling*100;
RefurbPP= (RefurbTooling/((BeamCost-RefurbPrice)*PP_Units))*Months;

StampROI = ((BeamCost-StampedPrice)*ROIUnits-StampedTooling)/StampedTooling*100;
StampPP= (StampedTooling/((BeamCost-StampedPrice)*PP_Units))*Months;

BlowROI = ((BeamCost-BlowPrice)*ROIUnits-BlowTooling)/BlowTooling*100;
BlowPP= (BlowTooling/((BeamCost-BlowPrice)*PP_Units))*Months;

InjectionROI = ((BeamCost-InjectionPrice)*ROIUnits-InjectionTooling)/InjectionTooling*100;
InjectionPP= (InjectionTooling/((BeamCost-InjectionPrice)*PP_Units))*Months;

CompROI = ((BeamCost-CompositePrice)*ROIUnits-CompositeTooling)/CompositeTooling*100;
CompPP= (CompositeTooling/((BeamCost-CompositePrice)*PP_Units))*Months;

fprintf('Part A:\n')
fprintf('Technology\t\t\t\t\t\t| ToolingCost\t| Part Price\t| ROI\t\t| Payback\n')
fprintf('-------------------------------------------------------------------------------------\n');
fprintf('Refurb & Upgrade Rolled Steel\t|  %2.0f \t\t| %2.2f\t\t\t| %2.2f\t\t| %2.0f\t\t\n',RefurbTooling,RefurbPrice,RefurbROI,RefurbPP)
fprintf('Stamped Steel\t\t\t\t\t| %2.0f \t\t| %2.2f\t\t\t| %2.2f\t\t| %2.0f\n',StampedTooling,StampedPrice,StampROI,StampPP)
fprintf('Blow Molded Plastic\t\t\t\t| %2.0f \t\t| %2.2f\t\t\t| %2.2f\t\t| %2.0f\n',BlowTooling,BlowPrice,BlowROI,BlowPP)
fprintf('Injection Molded Plastic\t\t| %2.0f \t\t| %2.2f\t\t\t| %2.2f\t\t| %2.0f\n',InjectionTooling,InjectionPrice,InjectionROI,InjectionPP)
fprintf('Composite\t\t\t\t\t\t|  %2.0f \t\t| %2.2f\t\t\t| %2.2f\t| %2.0f\n\n\n',CompositeTooling,CompositePrice,CompROI,CompPP)




%---Part B-------------------------------------
CapData       = xlsread('capdata.xls');
SteelData     = CapData(:,1);
BlownData     = CapData(:,2);
InjectionData = CapData(:,3);
Tolerance     = 0.25;

SteelAvg = mean(SteelData);
SteelDev = std(SteelData);
SteelCP  = Tolerance*(6*SteelDev);

BlownAvg = mean(BlownData);
BlownDev = std(BlownData);
BlownCP  = Tolerance*(6*BlownDev);

InjAvg = mean(InjectionData);
InjDev = std(InjectionData);
InjCP  = Tolerance*(6*InjDev);

fprintf('Part B:\n')
hist(SteelData)
title('Steel Analysis')
xlabel('Dimension')
ylabel('Frequency')
figure
hist(BlownData)
title('Blown Plastic Analysis')
xlabel('Dimension')
ylabel('Frequency')
figure
hist(InjectionData)
title('Injection Mold Analysis')
xlabel('Dimension')
ylabel('Frequency')

fprintf('\t\t\t\t| Average\t| Std-Dev\t| ROI\t\t\n')
fprintf('----------------------------------------------\n')
fprintf('Steel\t\t\t| %2.0f \t\t| %2.2f\t\t| %2.2f\t\t\n',SteelAvg,SteelDev,SteelCP)
fprintf('Stamped Steel\t| %2.0f \t\t| %2.2f\t\t| %2.2f\t\t\n',BlownAvg,BlownDev,BlownCP)
fprintf('Injected\t\t| %2.0f \t\t| %2.2f\t\t| %2.2f\t\t\n\n\n',InjAvg,InjDev,InjCP)




%---Part C-------------------------------------
StressSheet = xlsread('Tensiletestdata');
StrainData  = StressSheet(:,1);
StressData  = StressSheet(:,2);
YoungsMod   = (81111.4304113802-2569.5888351 )/(0.007922-0.004888);
YStrength   = YoungsMod*.002;

fprintf('Part C:\n')
fprintf('The ultimate strength is: %2.5f\n',max(StressData))
fprintf('The fracture strength is: %2.5f,%2.5f\n',StrainData(end),StressData(end))
fprintf('The young modulus is: %2.2f\n',YoungsMod) 
fprintf('The yield strength is: %2.2f\n',YStrength) 


figure
plot(StrainData,StressData,'r','linewidth',3,'markersize',4)
title('Stress-Strain Analysis')
xlabel('Strain (in./in.)')
ylabel('Stress (psi)')

















