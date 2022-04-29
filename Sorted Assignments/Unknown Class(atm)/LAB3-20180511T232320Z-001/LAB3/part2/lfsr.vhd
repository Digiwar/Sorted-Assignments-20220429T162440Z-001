
library ieee;
use ieee.std_logic_1164.all;

entity lfsr is
port(
	clk: in std_logic;
	q: out std_logic_vector(4 downto 1)
	);
end lfsr;

architecture archi of lfsr is
signal temp: std_logic_vector(4 downto 1) := "1000";
begin
	process(clk)
	begin
		if(rising_edge(clk)) then
			temp <= temp(3 downto 2) & (temp(1) xor temp(4)) & temp(4);
		end if;
	end process;
	q <= temp;
end archi; 

