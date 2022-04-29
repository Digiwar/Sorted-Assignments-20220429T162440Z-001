library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_UNSIGNED.ALL;
 

 
entity adder is
 
            	port(a, b : in std_logic_vector(1 downto 0);
                                            	y : out std_logic_vector(2 downto 0 ));
end adder;
 
architecture Behavioral of adder is
 
            	signal a1, b1 : std_logic_vector(2 downto 0 );
 
begin
 
            	a1 <= '0' & a;
            	b1 <= '0' & b;
 
            	y <= a1 + b1;
 
end Behavioral;

