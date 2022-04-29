`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 02/21/2018 03:35:43 PM
// Design Name: 
// Module Name: multi_tb
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


module multi_tb;
    
    reg [3:0] multicand,multiplier;
    reg RST,STRT,CLK,shft_con,loadP;
    reg ld1,ld2;
    reg [7:0] P;
    wire [7:0] display; 
    
    mult mul2(.display(display),.ld1(ld1),.ld2(ld2),.multicand(multicand),.P(P),.multiplier(multiplier),.RST(RST),.STRT(STRT),.loadP(loadP),.CLK(CLK),.shft_con(shft_con));
    initial
    begin
        multicand = 4'b1011; multiplier = 4'b1101; P = 0;
        #10
        ld1 <= 1; ld2 <= 0; STRT = 1; CLK=1; shft_con = 1; RST = 0; loadP = 0;
        #5
        CLK=0;
        #5
        CLK=1;
        #5
        CLK=0;
        #5
        CLK=1;
        #10
        ld1<=0; ld2 <= 1; CLK = 0;
        #5
        CLK=1;
        #5
        CLK=0;loadP = 1;
        #5
        CLK=1;loadP = 0;
        #5
        CLK=0;
        #10
        ld2 = 0; CLK=1;
        #10
        CLK=0;
        #5
        CLK=1; loadP = 1;
        #5
        CLK=0;
        #5
        CLK=1;
        #5
         CLK=0;
         #5
         CLK=1;
           #5
               CLK=0;
               #5
               CLK=1;
               #5
               CLK=0;
               #5
               CLK=1; loadP=0;
        $stop;
    
    end   
endmodule
