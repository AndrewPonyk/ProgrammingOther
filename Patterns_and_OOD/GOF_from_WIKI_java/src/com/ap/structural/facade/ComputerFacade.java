package com.ap.structural.facade;

class ComputerFacade {
    private CPU processor;
    private Memory ram;
    private HardDrive hd;
 
    private static int BOOT_ADDRESS = 0;
    private static int SECTOR_SIZE = 128;
    private static int BOOT_SECTOR = 800;
    
    public ComputerFacade() {
        this.processor = new CPU();
        this.ram = new Memory();
        this.hd = new HardDrive();
    }
 
    public void start() {
        processor.freeze();
        ram.load(BOOT_ADDRESS, hd.read(BOOT_SECTOR, SECTOR_SIZE));
        processor.jump(BOOT_ADDRESS);
        processor.execute();
    }
}