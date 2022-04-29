`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 02/28/2018 03:37:05 PM
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


module shiftp(loadP,P,carry,sum,shift,clk,rst);

input clk,rst,carry,shift,loadP;
input [3:0] sum;
output [7:0] P;

reg [3:0] ph,pl;
wire [7:0] P;

always @(posedge clk or posedge rst)
begin

    if(rst)
        ph <= 0;
    else if(loadP)
        ph <= sum;
    else if(shift)
        ph <= {carry, ph [3:1]};

end
always @(posedge clk or posedge rst)
begin
if(rst)
    pl <=0;    
else if(shift)
    pl <= {ph[0], pl[3:1]};
end
assign P = {ph,pl};
endmodule
