`include "ALUControl.v"

module ALUControl_fixture;

reg [3:0] controlIN;
wire [4:0] aLUControl;

ALUControl testALU(controlIN,aLUControl);

initial
begin
#1; $monitor("Output Signal = %b",aLUControl);
#1; controlIN = 4'b0000;
#1; controlIN = 4'b0001;
#1; controlIN = 4'b0010;
#1; controlIN = 4'b0011;
#1; controlIN = 4'b0100;
#1; controlIN = 4'b0101;
#1; controlIN = 4'b0110;
#1; controlIN = 4'b0111;
#1; controlIN = 4'b1000;
#1; controlIN = 4'b1001;
#1; controlIN = 4'b1010;
#1; controlIN = 4'b1011;
#1; $finish;
end

endmodule
