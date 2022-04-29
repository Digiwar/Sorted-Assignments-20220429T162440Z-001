
LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
ENTITY fsm_tb IS
END fsm_tb;
 
ARCHITECTURE behavior OF fsm_tb IS 
 
 
    COMPONENT fsm
    PORT(
         reset : IN  std_logic;
         clk : IN  std_logic;
         clr : OUT  std_logic;
         w : OUT  std_logic_vector(2 downto 0);
         ce : OUT  std_logic_vector(3 downto 0);
         sel : OUT  std_logic_vector(1 downto 0);
         s : OUT  std_logic_vector(2 downto 0)
        );
    END COMPONENT;
    

   signal reset : std_logic := '0';
   signal clk : std_logic := '0';

   signal clr : std_logic;
   signal w : std_logic_vector(2 downto 0);
   signal ce : std_logic_vector(3 downto 0);
   signal sel : std_logic_vector(1 downto 0);
   signal s : std_logic_vector(2 downto 0);

   constant clk_period : time := 10 ns;
 
BEGIN
 
   uut: fsm PORT MAP (
          reset => reset,
          clk => clk,
          clr => clr,
          w => w,
          ce => ce,
          sel => sel,
          s => s
        );

   clk_process :process
   begin
		clk <= '0';
		wait for clk_period/2;
		clk <= '1';
		wait for clk_period/2;
   end process;
 


   stim_proc: process
   begin		
      
		reset <= '1';
      wait for 10 ns;	

		reset <= '0';
      wait for 150 ns;

      -- insert stimulus here 

      wait;
   end process;

END;
