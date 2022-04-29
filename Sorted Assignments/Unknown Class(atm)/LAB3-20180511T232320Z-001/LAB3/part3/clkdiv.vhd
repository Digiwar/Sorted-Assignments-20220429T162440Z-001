
library ieee;
use  ieee.std_logic_1164.all;
use  ieee.std_logic_unsigned.all;
use  ieee.std_logic_arith.all;
 
entity clkdiv is
port(
 
            	CLK1: in std_logic;
            	CLK2: out std_logic
 
);
end clkdiv;
 
architecture Behavioral of clkdiv is
 
signal cnt : std_logic_vector(25 downto 0) := (others => '0');
 
begin
            	process(CLK1)
            	begin
            	
                            	if (rising_edge(CLK1)) then
                            	
                                            	if (cnt = 49999999) then
                                                            	CLK2 <= '1';
                                                            	cnt <= (others => '0');
                                            	elsif (cnt < 24999999) then
                                                            	CLK2 <= '1';
                                                            	cnt <= cnt + '1';
                                            	else
                                                            	CLK2 <= '0';
                                                            	cnt <= cnt + '1';
                                            	end if;
                            	
                            	end if;
            	
            	end process;
 
end Behavioral;
