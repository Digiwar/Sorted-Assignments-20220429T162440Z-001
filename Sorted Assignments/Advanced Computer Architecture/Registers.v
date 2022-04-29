//Register File

module Registers(ReadReg1, ReadReg2, WriteReg1, WriteReg2, WriteData1, WriteData2, RegOut1, RegOut2, WriteEnable, clk, rst);

//I/O
input [3:0] ReadReg1, ReadReg2, WriteReg1, WriteReg2;
input [15:0] WriteData1, WriteData2;
input WriteEnable, clk, rst;
output reg [15:0] RegOut1, RegOut2;
reg [32:0] reg_bank [15:0];

always @ (*)
begin
     	RegOut1 = reg_bank[ReadReg1];
        RegOut2 = reg_bank[ReadReg2];
end

always @(posedge clk, negedge rst)
begin
  if (!rst) begin
        reg_bank[0] <= 'h0F00;
        reg_bank[1] <= 'h0050;
        reg_bank[2] <= 'hFF0F;
        reg_bank[3] <= 'hFF0F;
        reg_bank[4] <= 'h0040;
        reg_bank[5] <= 'h6666;
        reg_bank[6] <= 'h00FF;
        reg_bank[7] <= 'hFF77;
        reg_bank[8] <= 'h0000;
        reg_bank[9] <= 'h0000;
        reg_bank[10] <= 'h0000;
        reg_bank[11] <= 'hCC89;
        reg_bank[12] <= 'h0002;
        reg_bank[13] <= 'h0000;
        reg_bank[14] <= 'h0000;
        reg_bank[15] <= 'h0000;
  end

  else begin
    if (WriteEnable) begin
      reg_bank[WriteReg1] <= WriteData1;
      reg_bank[WriteReg2] <= WriteData2;
    end
  end

end


endmodule