package com.skuu.design.factory.cxgc.factory;

import com.skuu.design.factory.cxgc.Browser;
import com.skuu.design.factory.cxgc.OperatingSystem;
import com.skuu.design.factory.cxgc.SystemFactory;
import com.skuu.design.factory.cxgc.bo.ChromeBrowser;
import com.skuu.design.factory.cxgc.bo.WindowsOS;

/**
 * 8. 具体工厂1 - Windows系统工厂
 */
public class WindowsSystemFactory implements SystemFactory {
    @Override
    public OperatingSystem createOperatingSystem() {
        return new WindowsOS();
    }

    @Override
    public Browser createBrowser() {
        return new ChromeBrowser();
    }
}