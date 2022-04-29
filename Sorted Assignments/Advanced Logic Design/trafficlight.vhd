library ieee;
use ieee.std_logic_1164.all;
use ieee.std_logic_arith.all;
use ieee.std_logic_unsigned.all;

entity traffic is
port( clk : in std_logic;
      reset : in std_logic;
      r, g, y: out std_logic);
end traffic;

architecture beh of traffic is
 signal cnt : std_logic_vector(3 downto 0):="0000";
 type st is (s0, s1, s2);
 signal cs : st;
 
begin
process(clk, reset)
begin
     if(reset = '1') then
          cs <= s0;
          cnt <= (others => '0');
     elsif (rising_edge(clk)) then  
       case(cs) is
       when s0 => cnt <= cnt + 1;
               if (cnt = 8 ) then
                  cs <= s1;                   
               else
                  cs <= s0;
               end if;
       when s1 => cnt <= cnt +1;
                 if ( cnt = 12) then
                   cs <= s2;
                 else
                   cs <= s1;
                 end if; 
       when s2 =>  cnt <= cnt + 1;
                  if ( cnt = 15 ) then 
                   cs <= s0;
                  else
                   cs <= s2;
                  end if;
       when others =>  cs  <= s0;
       end case;
     end if;
end process;

process(cs)
begin
     case(cs) is
     when s0 => r <= '1'; g <= '0'; y <= '0';
     when s1 => r <= '0'; g <= '0'; y <= '1';
     when s2 => r <= '0'; g <= '1'; y <= '0'; 
     when others =>r <= '0'; g <= '0'; y <= '0'; 
     end case;
end process;
end beh;

