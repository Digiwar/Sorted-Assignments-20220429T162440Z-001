//Hazard Detection Unit



module hazardUnit(errBit,bubMux,ifIDBLK,pcBLK);


input [12:0] errBit;
 

output reg bubMux,ifIDBLK,pcBLK;




always @ (*)
begin

if(errBit[12] & ((errBit[11:8] == errBit[3:0])|(errBit[7:4]==errBit[3:0])))
begin
	ifIDBLK = 0;
	pcBLK = 0;
	bubMux = 0;
end
else
begin
	ifIDBLK = 1;
	pcBLK = 1;
	bubMux = 1;
end
end
endmodule
