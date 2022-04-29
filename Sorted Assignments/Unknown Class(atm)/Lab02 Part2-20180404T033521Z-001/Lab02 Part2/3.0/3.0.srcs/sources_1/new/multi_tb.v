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
    reg RST,CLK,shft_con,loadP,shftP;
    reg ld1,ld2;
    wire [8:0] P;
    wire [3:0] dispA,dispB,dispMultc,dispMulti;
    wire [7:0] display; 
    wire dispS;
    mult mul2(.dispS(dispS),.dispMultc(dispMultc),.dispMulti(dispMulti),.dispA(dispA),.dispB(dispB),.display(display),.ld1(ld1),.ld2(ld2),.multicand(multicand),.P(P),.multiplier(multiplier),.RST(RST),.shftP(shftP),.loadP(loadP),.CLK(CLK),.shft_con(shft_con));
    initial CLK =0;
    always #10 CLK = ~ CLK;
    
    initial
    begin
        multiplier = 5'b11101; multicand = 4'b1011;
        RST=1;        ld1 <=1; ld2 <=1;
        shft_con <=0;shftP<=0;loadP<=0;
        #20
        RST=0;shft_con <=0;shftP<=0;loadP<=0;
        #20      
        ld2 <=0;ld1<=0;
      
        shft_con <=0;shftP<=0;loadP<=1;
        #20
        shft_con <=0;shftP<=1;loadP<=0;
        #20
        shft_con <=1;shftP<=0;loadP<=0;
        #20
        shft_con <=0;shftP<=0;loadP<=1;
        #20
        shft_con <=0;shftP<=1;loadP<=0;
        #20
        shft_con <=1;shftP<=0;loadP<=0;
        #20
        shft_con <=0;shftP<=0;loadP<=1;              
        #20
        shft_con <=0;shftP<=1;loadP<=0;
        #20
        shft_con <=1;shftP<=0;loadP<=0;
        #20
        shft_con <=0;shftP<=0;loadP<=1;
        #20
        shft_con <=0;shftP<=1;loadP<=0;
        #20
        shft_con <=1;shftP<=0;loadP<=0;
        #20
        shft_con <=0;shftP<=0;loadP<=1;
        #20
        shft_con <=0;shftP<=1;loadP<=0;
        #20
        shft_con <=1;shftP<=0;loadP<=0;
        #20
        shft_con <=0;shftP<=0;loadP<=1;
        #20
        shft_con <=0;shftP<=1;loadP<=0;
        #20
        shft_con <=1;shftP<=0;loadP<=0;
        
        #40
        
        
        $stop;
    
    end   
    
    initial $monitor($time," adder output = %b %b, ph = %b", mul2.s, mul2.P, mul2.n);
        
endmodule
