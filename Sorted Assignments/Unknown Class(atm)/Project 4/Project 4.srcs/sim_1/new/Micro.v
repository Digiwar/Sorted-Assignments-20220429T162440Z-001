`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 05/11/2018 12:06:23 PM
// Design Name: 
// Module Name: Micro
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


module Micro(M0,M1,M2,Cin,CLR,W,CE,SEL,S,CLK,dout);
    input M0,M1,M2,Cin,CLR,CLK;
    input [2:0] W,S;
    input  [1:0] SEL;
    input  [3:0] CE;
    output [2:0] dout;

    wire muxO;
    wire  O;
    wire [2:0] R;
    wire [3:0] Y;
    
    mux dff1(W[0],O,M0,Y[0]);
    mux dff2(W[1],O,M1,Y[1]);
    mux dff3(W[2],O,M2,Y[2]);
    
    dffb dffS1(CLK,CLR,CE[0],Y[0],R[0]);
    dffb dffS2(CLK,CLR,CE[1],Y[1],R[1]);
    dffb dffS3(CLK,CLR,CE[2],Y[2],R[2]);
    
    multi4 fourMul(SEL,4'b0000,R[2],R[1],R[0],muxO);
    ALU Arith(muxO, O,S,Cin,Y[3]);
    
    dffb dffS4(CLK,CLR,CE[3],Y[3],O);
    
    assign dout = R[2];

endmodule
