//Buffers


module exMEMBuff(data_in_bus, data_out_bus,clk,rst);



//I/O

input [58:0] data_in_bus;
input  clk,rst;
output reg [58:0] data_out_bus;

always @ (posedge clk or negedge rst)
	begin
		if(rst)
			data_out_bus <= 0;
		else
			data_out_bus <= data_in_bus;
	end

endmodule

