library ieee;
use ieee.std_logic_1164.all;

entity dff is 
port(
	  --clk: in std_logic;
		  d:in std_logic_vector(3 downto 0);
		  load:in std_logic;
		  q:out std_logic_vector(3 downto 0)
	 );
end dff;
architecture beh of dff is 
signal temp: std_logic_vector(3 downto 0); 
begin
	process(load)
	begin
		if(rising_edge(load)) then
			temp <= d;
		end if;
	end process;
q <= temp;
end beh;
