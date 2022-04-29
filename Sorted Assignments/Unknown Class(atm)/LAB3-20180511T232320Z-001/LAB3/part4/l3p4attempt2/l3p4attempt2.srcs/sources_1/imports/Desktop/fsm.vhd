library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_unsigned.all;
use IEEE.std_logic_arith.all;

entity fsm is

port ( clk, reset : IN std_logic;
       R,G,Y : OUT std_logic );

end fsm;

architecture beh of fsm is

type state_type is (s1,s2,s3);

signal state: state_type ;

signal count : STD_LOGIC_VECTOR(3 downto 0);
begin


process (clk,reset)
begin
if (reset ='1') then
   count <= "0000";
   state <=s1;
elsif (clk='1' and clk'event) then
  case state is
  when s1 =>
        count <= count + 1;
        if (count < 8) then
          state <= s1;
         -- count <= count+1;
        elsif (count = 8) then
          state <= s2;
          count <= "0000";
        end if;

  when s2 => 
        count <= count + 1;
          if (count < 8) then
            state <= s2;
            --count <= count+1;
          elsif (count = 8) then
            state <= s3;
            count <="0000";
          end if;
  when s3 => 
  count <= count + 1;
          if (count < 8) then
            state <= s3;
          elsif (count = 8) then
            state <= s1;
          end if;

  when others => state <= s1;
  end case;

end if;

end process;

process(state)

begin

case state is

  when s1 =>
       R <='1';
       G <='0';
       Y <='0';
  when s2 =>
      R <='0';
      G <='0';
      Y <='1';

  when s3 =>
      R <='0';
      G <='1';
      Y <='0';

  when others=>
       R <='0';
       G <='0';
       Y <='0';        

end case;

end process;

end beh;
