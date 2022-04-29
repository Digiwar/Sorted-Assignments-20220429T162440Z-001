library ieee;
use ieee.std_logic_1164.all;

entity top_tb is
end top_tb;

architecture beh of top_tb is
component top
port(
    clk,reset       : in    std_logic;
    sw                  : in  std_LOGIC_VECTOR(3 downto 0); --idle,disp_message,disp_hamming,hold
    output          : out std_logic_vector(7 downto 1)
    );
end component;

signal   clk,reset      :   std_logic;
signal  sw                  :  std_LOGIC_VECTOR(3 downto 0); --idle,disp_message,disp_hamming,hold
signal  output          :  std_logic_vector(7 downto 1);

begin
    uut: top port map(clk=>clk,reset=>reset,sw=>sw,output=>output);
    process
    begin
        clk <= '0'; wait for 5 ns;
        clk <= '1'; wait for 5 ns;
    end process;
    
    process
    begin
        reset <= '1';
        sw <= "0000";
        wait for 10 ns;
        
        reset <= '0';
        sw <= "0001";
        wait for 300 ns;
        
        sw <= "0010";
        wait for 300 ns;
        
        sw <= "1010";
        wait for 300 ns;
        
        sw <= "0100";

        wait for 300 ns;
        
        sw <= "1000";
        wait for 300 ns;
        
        sw <= "0010";
        wait for 300 ns;
    
        sw <= "0001";
        wait for 300 ns;
        wait;
    end process;
end beh;