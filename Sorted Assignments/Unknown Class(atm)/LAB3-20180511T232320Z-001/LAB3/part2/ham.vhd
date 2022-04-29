library ieee;
use ieee.std_logic_1164.all;

entity hc is
port(
		d: in std_logic_vector(4 downto 1);
		dout: out std_logic_vector(7 downto 1)
	  );
end hc;

architecture hamming of hc is 
signal p3,p2,p1: std_logic;
	begin
		p3 <= d(4) xor d(3) xor d(2);
		p2 <= d(4) xor d(3) xor d(1);
		p1 <= d(4) xor d(2) xor d(1);
	   dout(7) <= d(4);
		dout(6) <= d(3);
		dout(5) <= d(2);
		dout(4) <= p3;
		dout(3) <= d(1);
		dout(2) <= p2;
		dout(1) <= p1;
end hamming;
