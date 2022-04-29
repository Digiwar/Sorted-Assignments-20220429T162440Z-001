`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 02/14/2018 02:32:01 PM
// Design Name: 
// Module Name: mult
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


module mult(display,multicand,multiplier,RST,P, CLK,shft_con,ld1,ld2,loadP,dispA,dispB,shftP,dispMulti,dispMultc);
    parameter GRND = 4'b0000;
    
    output [3:0] dispA,dispB,dispMulti,dispMultc;
    input [3:0] multicand;
    input [4:0] multiplier;
    
    input RST,  CLK, shft_con, ld1,ld2,loadP,shftP;
    wire [3:0] j,l,n;
    wire [4:0] k;
    wire [3:0] m;
    wire s;

    output [7:0]display;
    
    //assign l = 4'b0111;
    assign l = P[7:4];
    output [8:0] P;
   
   
            dffa load1(.clk(CLK),.clr(RST),.da(multicand),.load(ld1),.qa(j));
            dffb load2(.clk(CLK),.clr(RST),.db(multiplier),.qb(k),.sft(shft_con),.load(ld2));
            mux choose1(.s(k[0]),.d1(GRND),.d0(j),.y(n));
            adder add1(.a(l),.b(n),.cout(s),.s(m));
            shiftp shift1(.RST(RST),.CLK(CLK),.mostsig(s),.data_in(m),.shift_sig(shftP),.loadP(loadP),.prod(P));
   
    assign dispMultc = j;
    assign dispMulti = k;
    assign dispA = n;
    assign dispB = m;
    assign display = l;
endmodule
