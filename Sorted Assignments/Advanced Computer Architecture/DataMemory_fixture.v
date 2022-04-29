`include "DataMemory.v"

module DMFixture;

reg [15:0] addr_in, data_in;
wire [15:0] data_out;
reg clk, rst, WriteEnable;
integer i;
DM dm1(.addr_in(addr_in), .data_in(data_in), .data_out(data_out), .WriteEnable(WriteEnable), .clk(clk), .rst(rst));

   initial begin
      $display("Time\tdata_in\t\t\taddr_in\t\t\tWE\tdata_out              \clk    \rst");
      $display("-------------------------------------------------------------------------------------------------------------");
      $monitor("%0d\t%b\t%b\t%b\t%b\t%b\t%b", $time, data_in, addr_in, WriteEnable, data_out, clk, rst);
   end

   initial begin
     rst = 0;
     #10;
     rst = 1;
     for (i = 0; i < 16; i = i + 1) begin
       addr_in = i;
       #5;
     end

     for (i = 0; i < 16; i = i + 1) begin
       addr_in = i;
       #5;
     end
   end

initial begin
#80
     WriteEnable = 1;
     addr_in = 4'b0000;
     data_in = 4'b1111;
     #10
     WriteEnable = 0;
end


// Setup the clock to toggle every 10 time units
initial
begin
       clk = 1'b0;
       forever #5 clk = ~clk;
end

// Finish the simulation at time 200
initial
begin
       #200 $finish;
end
endmodule