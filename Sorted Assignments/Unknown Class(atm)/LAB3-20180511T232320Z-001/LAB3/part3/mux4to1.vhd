
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
 
entity mux4to1 is
                                            	port (sel : in std_logic_vector(1 downto 0);
                                            	COMPARE, ADD, OR1, AND1: in std_logic_vector(3 downto 0);
                                            	Y : out std_logic_vector(3 downto 0));
end mux4to1;
 
architecture Behavioral of mux4to1 is
signal W : std_logic_vector(3 downto 0 );
begin
            	process(sel, COMPARE, ADD, OR1, AND1)
            	begin
            	
                            	if (sel = "00") then
                                            	W <= COMPARE;
                            	elsif (sel = "01") then
                                            	W <= ADD;
                            	elsif (sel = "10") then
                                            	W <= OR1;
                            	elsif (sel = "11") then
                                            	W <= AND1;
                            	end if;
            	
            	end process;
            	
            	Y <= W;
 
end Behavioral;

