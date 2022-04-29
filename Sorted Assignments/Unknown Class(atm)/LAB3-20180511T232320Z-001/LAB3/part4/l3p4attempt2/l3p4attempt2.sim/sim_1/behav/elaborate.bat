@echo off
set xv_path=C:\\Xilinx\\Vivado\\2017.2\\bin
call %xv_path%/xelab  -wto 70ea9d634de84047b1034e099425324b -m64 --debug typical --relax --mt 2 -L xil_defaultlib -L secureip --snapshot fsm_tb_behav xil_defaultlib.fsm_tb -log elaborate.log
if "%errorlevel%"=="0" goto SUCCESS
if "%errorlevel%"=="1" goto END
:END
exit 1
:SUCCESS
exit 0
