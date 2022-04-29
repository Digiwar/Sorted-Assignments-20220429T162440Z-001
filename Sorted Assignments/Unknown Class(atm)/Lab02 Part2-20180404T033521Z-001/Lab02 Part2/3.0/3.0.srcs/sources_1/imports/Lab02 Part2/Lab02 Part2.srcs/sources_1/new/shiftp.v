`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 03/07/2018 02:10:59 PM
// Design Name: 
// Module Name: shiftp
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


module shiftp(CLK,RST,mostsig,data_in,loadP,shift_sig,prod);
input shift_sig,mostsig,CLK,RST,loadP;

input [3:0] data_in;
output [8:0] prod;


reg [3:0] upper, lower;


always @ (posedge CLK)
begin
    if(RST)
        upper <= 0;
    else if(shift_sig)
    begin
        upper[4] <= mostsig;
        upper[3] <= upper[4];
        upper[2] <= upper[3];
        upper[1] <= upper[2];
        upper[0] <= upper[1];
    end
    else if(loadP)
        upper <= data_in;
end

always @(posedge CLK)
begin
    if(RST)
        lower <=0;
    else if(shift_sig)
    begin
        lower[3] <= upper[0];
        lower[2] <= lower[3];
        lower[1] <= lower[2];
        lower[0] <= lower[1];
    end
end
        
assign prod = {mostsig,upper,lower};

endmodule
