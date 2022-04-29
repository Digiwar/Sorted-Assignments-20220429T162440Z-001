library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;
use ieee.std_logic_arith.all;

entity clkdiv is
port(
		clk1: in std_logic;
		clk2: out std_logic
	 );
end clkdiv;
architecture archi of clkdiv is
signal cnt : std_logic_vector(26 downto 0);
signal clkout: std_logic;
begin
	process(clk1)
		begin
			if(rising_edge(clk1)) then
				if(cnt = 49999999) then
					cnt <= "000000000000000000000000000";
					clkout <= '1';
				elsif(cnt < 24999999) then
					cnt <= cnt + 1;
					clkout <= '1';
				else
					 cnt <= cnt + 1;
					 clkout <= '0';
				end if;
			end if;
	end process;
clk2 <= clkout;
end archi; 
