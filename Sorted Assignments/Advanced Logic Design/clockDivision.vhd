library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;
use ieee.std_logic_unsigned.all;
use IEEE.STD_LOGIC_ARITH.ALL;

entity clockDivision is
	port(
		osc:	in std_logic;
		clkref1: out std_logic;
		clkref2: out std_logic
	);
end clockDivision;

architecture beh of clockDivision is
signal clk1: std_logic;
signal clk2: std_logic;
signal cnt1: std_logic_vector(7 downto 0);-- := "0000000000000000000";
signal cnt2: std_logic_vector(18 downto 0);

begin
	process(osc)
	begin 
		if rising_edge (osc) then
			if (cnt1 = 99) then
				cnt1 <= (others => '0');
				clk1 <= '1';
			elsif (cnt1 < 49) then
				cnt1 <= cnt1 + 1;
				clk1 <= '1';
			else
				cnt1 <= cnt1 + 1;
				clk1 <= '0';
			end if;
		end if;
	end process;
	clkref1 <= clk1;
	
	process(osc)
	begin 
		if rising_edge (osc) then
			if (cnt2 = 499999) then
				cnt2 <= (others => '0');
				clk2 <= '1';
			elsif (cnt2 < 249999) then
				cnt2 <= cnt2 + 1;
				clk2 <= '1';
			else
				cnt2 <= cnt2 + 1;
				clk2 <= '0';
			end if;
		end if;
	end process;
	clkref2 <= clk2;
end beh;