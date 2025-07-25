package com.possible_triangle.multikulti.test

import com.tterrag.registrate.AbstractRegistrate
import net.minecraftforge.eventbus.api.IEventBus

open class ForgeMultikultiRegistrate(modid: String) : AbstractRegistrate<ForgeMultikultiRegistrate>(modid) {

    private var modBus: IEventBus? = null

    public override fun currentName(): String {
        return super.currentName()
    }

    public override fun registerEventListeners(bus: IEventBus): ForgeMultikultiRegistrate {
        modBus = bus
        return super.registerEventListeners(bus)
    }

    override fun getModEventBus(): IEventBus {
        return modBus ?: throw NullPointerException("registrate not registered yet")
    }

}