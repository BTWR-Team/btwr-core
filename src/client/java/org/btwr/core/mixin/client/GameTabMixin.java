package org.btwr.core.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.tab.GridScreenTab;
import net.minecraft.client.gui.widget.*;
import net.minecraft.text.Text;
import org.btwr.core.gui.BTWRDifficultyScreenHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.client.gui.screen.world.CreateWorldScreen$GameTab")
public abstract class GameTabMixin extends GridScreenTab {

    @Unique private final BTWRDifficultyScreenHelper btwrHelper = new BTWRDifficultyScreenHelper();

    protected GameTabMixin(Text title) {
        super(title);
    }

    // Hides vanilla's own difficulty CyclingButtonWidget so our custom one can take its place.
    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/GridWidget$Adder;add(Lnet/minecraft/client/gui/widget/Widget;Lnet/minecraft/client/gui/widget/Positioner;)Lnet/minecraft/client/gui/widget/Widget;", ordinal = 2))
    private <T extends Widget> T hideVanillaDifficultyButton(GridWidget.Adder instance, T widget, Positioner positioner) {
        return widget;
    }

    @Inject(method = "<init>", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/widget/CyclingButtonWidget$Builder;build(IIIILnet/minecraft/text/Text;Lnet/minecraft/client/gui/widget/CyclingButtonWidget$UpdateCallback;)Lnet/minecraft/client/gui/widget/CyclingButtonWidget;",
            ordinal = 1,
            shift = At.Shift.AFTER
    ))
    private void addDifficultyButton(CreateWorldScreen screen, CallbackInfo ci, @Local GridWidget.Adder adder, @Local Positioner positioner) {
        /**
        DirectionalLayoutWidget row = btwrHelper.buildCreateWorld(
                BTWRWorldCreationData.getSelected(),
                BTWRWorldCreationData.isLocked(),
                selected -> {
                    BTWRWorldCreationData.setSelected(selected);
                    btwrHelper.setDifficultyForcesLock(selected == ModDifficulties.CLASSIC);
                },
                (currentScreen, confirmed) -> {
                    CreateWorldScreen cws = (CreateWorldScreen)currentScreen;
                    assert cws != null;
                    cws.client.setScreen(cws);
                    if (confirmed) {
                        BTWRWorldCreationData.setLocked(true);
                        btwrHelper.applyManualLock(true);
                    }
                }
        );
        **/

        //adder.add(row, positioner);

        // Apply the classic-forces-lock rule to whatever's selected right now too.
        //btwrHelper.setDifficultyForcesLock(BTWRWorldCreationData.getSelected() == ModDifficulties.CLASSIC);

        screen.getWorldCreator().addListener(creator -> {
            boolean cheatsEnabled = creator.areCheatsEnabled();
            if (!cheatsEnabled) {
                //BTWRWorldCreationData.setLocked(true);
            }
        });
    }

}