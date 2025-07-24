package com.possible_triangle.multikulti.registrate

import com.tterrag.registrate.AbstractRegistrate
import net.minecraftforge.eventbus.api.IEventBus

open class MultikultiRegistrate(modid: String) : AbstractRegistrate<MultikultiRegistrate>(modid) {

    private var modBus: IEventBus? = null

    public override fun currentName(): String {
        return super.currentName()
    }

    public override fun registerEventListeners(bus: IEventBus): MultikultiRegistrate {
        modBus = bus
        return super.registerEventListeners(bus)
    }

    override fun getModEventBus(): IEventBus {
        return modBus ?: throw NullPointerException("registrate not registered yet")
    }

}