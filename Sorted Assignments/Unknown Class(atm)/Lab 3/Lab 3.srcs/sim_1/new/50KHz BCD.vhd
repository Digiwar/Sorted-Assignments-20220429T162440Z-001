----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date: 04/03/2018 11:04:07 PM
-- Design Name: 
-- Module Name: 50KHz BCD - Behavioral
-- Project Name: 
-- Target Devices: 
-- Tool Versions: 
-- Description: 
-- 
-- Dependencies: 
-- 
-- Revision:
-- Revision 0.01 - File Created
-- Additional Comments:
-- 
----------------------------------------------------------------------------------


library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.numeric_std.ALL;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx leaf cells in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity 50KHz BCD is
  Port (clk,reset: in std_logic;
  clk_out: out std_logic);
end 50KHz BCD;

architecture Behavioral of 50KHz BCD is

    signal count: integer := 1;
    signal tmp: std_logic := '0';

begin
    
    process(clk,reset)
    begin
    if(reset = '1') then
    count <=1;
    tmp <= '0';
    elseif (rising_edge(clk)) then
    count <= count+1;
    if(count = 25000)
    tmp<= NOT tmp;
    count<=1;
    end if;
    end if;
    clock_out <=tmp;
    end process;
    
end Behavioral;
