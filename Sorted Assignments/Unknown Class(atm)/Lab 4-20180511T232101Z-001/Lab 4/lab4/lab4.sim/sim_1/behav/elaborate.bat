@echo off
set xv_path=C:\\Xilinx\\Vivado\\2017.2\\bin
call %xv_path%/xelab  -wto d07bf84aefbb40539651b850f49a8747 -m64 --debug typical --relax --mt 2 -L xil_defaultlib -L secureip --snapshot datapath_tb_behav xil_defaultlib.datapath_tb -log elaborate.log
if "%errorlevel%"=="0" goto SUCCESS
if "%errorlevel%"=="1" goto END
:END
exit 1
:SUCCESS
exit 0
