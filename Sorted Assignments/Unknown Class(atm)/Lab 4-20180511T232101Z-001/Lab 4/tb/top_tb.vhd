
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
 
ENTITY top_tb IS
END top_tb;
 
ARCHITECTURE behavior OF top_tb IS 
 
 
    COMPONENT top
    PORT(
         sw1 : IN  std_logic;
         clk : IN  std_logic;
         cin : IN  std_logic;
         m : IN  std_logic_vector(2 downto 0);
         dout : OUT  std_logic
        );
    END COMPONENT;
    

   signal sw1 : std_logic := '0';
   signal clk : std_logic := '0';
   signal cin : std_logic := '0';
   signal m : std_logic_vector(2 downto 0) := (others => '0');

   signal dout : std_logic;

   constant clk_period : time := 10 ns;
 
BEGIN
 
	--//////////////////////////////////////////////
	
   uut: top PORT MAP (
          sw1 => sw1,
          clk => clk,
          cin => cin,
          m => m,
          dout => dout
        );

   --/////////////////////////////////////////////
	
   clk_process :process
   begin
		clk <= '0';
		wait for clk_period/2;
		clk <= '1';
		wait for clk_period/2;
   end process;
 
   --//////////////////////////////////////////////

   -- Stimulus process
   stim_proc: process
   begin		
      
		sw1 <= '1';
		cin <= '1';
		m <= "011";
      wait for 10 ns;	

		sw1 <= '0';
		cin <= '1';
		m <= "011";
      wait for 150 ns;

      

      wait;
   end process;

END;
