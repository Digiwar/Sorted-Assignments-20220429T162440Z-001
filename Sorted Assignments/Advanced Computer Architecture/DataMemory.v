//Data Memory

module DM(addr_in, data_in, data_out, WriteEnable, clk, rst);

//I/O
input [3:0] addr_in;
input [15:0] data_in;
input clk, rst, WriteEnable;
output reg [15:0] data_out;
reg [32:0] data_bank [15:0];

always @ (*)
begin
     	data_out = data_bank[addr_in];
end

always @ (posedge clk, negedge rst)

   if (!rst) begin
        data_bank[0] <= 'h3ADC;
        data_bank[1] <= 'h0000;
        data_bank[2] <= 'h1342;
        data_bank[3] <= 'hADDE;
        data_bank[4] <= 'hEFBE;
        data_bank[5] <= 'hFFFF;
        data_bank[6] <= 'hAAAA;
        data_bank[7] <= 'h0000;
        data_bank[8] <= 'h0000;
        data_bank[9] <= 'h0000;
        data_bank[10] <= 'h0000;
        data_bank[11] <= 'h0000;
        data_bank[12] <= 'h0000;
        data_bank[13] <= 'h0000;
        data_bank[14] <= 'h0000;
        data_bank[15] <= 'h0000;
  end

  else begin
    if (WriteEnable)
      data_bank[addr_in] <= data_in;
  end



endmodule