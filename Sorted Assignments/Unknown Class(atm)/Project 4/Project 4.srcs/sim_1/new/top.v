`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 05/11/2018 12:05:04 PM
// Design Name: 
// Module Name: top
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////


module top_TB;

    reg M0,M1,M2,Cin;
    reg RESET,CLK;
    wire dout,CLR;
    wire [2:0] W,S;
    wire [3:0] CE;
    wire [1:0] SEL;
    
Micro Micro1(M0,M1,M2,Cin,CLR,W,CE,SEL,S,CLK,dout);
fsm Fsm(RESET,CLK,CLR,W,CE,SEL,S);

initial
begin
    M0 = 1'b1;
    M1 = 1'b1;
    M2 = 1'b0 ;
    Cin = 1'b0;
    RESET = 1;
    CLK = 1;
    #5
    RESET = 0;
    CLK=0;
    #5
    CLK = 1;
    #5
    CLK=0;
    #5
    CLK = 1;
    #5
    CLK=0;
    #5
    CLK = 1;
    #5
    CLK=0;
    #5
    CLK = 1;
    #5
    CLK=0;
        #5
    CLK = 1;
    #5
    CLK=0;
    #5
    CLK = 1;
    #5
    CLK=0;
    #5
    CLK = 1;
    #5
    CLK=0;
    #5
    CLK = 1;
    #5
    CLK=0;
        #5
    CLK = 1;
    #5
    CLK=0;
    #5
    CLK = 1;
    #5
    CLK=0;
    #5
    CLK = 1;
    #5
    CLK=0;
    #5
    CLK = 1;
    #5
    CLK=0;
        #10
    CLK = 1;
    #10
    CLK=0;
    #10
    CLK = 1;
    #10
    CLK=0;
    #10
    CLK = 1;
    #10
    CLK=0;
    #10
    CLK = 1;
    #10
    CLK=0;
        #10
    CLK = 1;
    #10
    CLK=0;
    #10
    CLK = 1;
    #10
    CLK=0;
    #10
    CLK = 1;
    #10
    CLK=0;
    #10
    CLK = 1;
    #10
    CLK=0;
        #10
    CLK = 1;
    #10
    CLK=0;
    #10
    CLK = 1;
    #10
    CLK=0;
    #10
    CLK = 1;
    #10
    CLK=0;
    #10
    CLK = 1;
    #10
    CLK=0;
    $stop;
    end
 
                   
endmodule
