package com.skuu.design.factory.cxgc.factory;

import com.skuu.design.factory.cxgc.Browser;
import com.skuu.design.factory.cxgc.OperatingSystem;
import com.skuu.design.factory.cxgc.SystemFactory;
import com.skuu.design.factory.cxgc.bo.MacOS;
import com.skuu.design.factory.cxgc.bo.SafariBrowser;

/**
 * 9. 具体工厂2 - macOS系统工厂
 */
public class MacSystemFactory implements SystemFactory {
    @Override
    public OperatingSystem createOperatingSystem() {
        return new MacOS();
    }

    @Override
    public Browser createBrowser() {
        return new SafariBrowser();
    }
}