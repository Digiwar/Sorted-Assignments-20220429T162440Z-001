library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_unsigned.all;

entity mux is
	port(	lfsr: in std_logic_vector(3 downto 0);
			ham: in std_logic_vector(7 downto 1);
			s: in std_logic_vector(2 downto 1);
			y: out std_logic_vector(7 downto 1));
end mux;

architecture beh of mux is
signal p: std_logic_vector(6 downto 0);
begin
	process (s, lfsr, ham)
	begin
		if(s = "00") then
			p <= "0000000"; --reset 
		elsif(s = "01") then
			p <= "000" & lfsr; --switch 2
		elsif(s = "10") then
			p <= ham; -- switch 3
		else
			p <= "0000000";
		end if;
	end process;
	y <= p;
end beh;
