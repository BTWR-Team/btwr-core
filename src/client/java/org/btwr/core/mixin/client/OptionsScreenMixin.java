package org.btwr.core.mixin.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.*;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.btwr.core.gui.BTWRDifficultyScreenHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OptionsScreen.class)
public abstract class OptionsScreenMixin extends Screen {

    @Shadow @Final private GameOptions settings;

    @Unique private final BTWRDifficultyScreenHelper screenHelper = new BTWRDifficultyScreenHelper();

    protected OptionsScreenMixin(Text title) {
        super(title);
    }

    //@Inject(method = "createTopRightButton", at = @At("HEAD"), cancellable = true)
    private void replaceDifficultyWidget(CallbackInfoReturnable<Widget> cir) {
        //cir.setReturnValue(createTopRightButton());
    }

    /**
    @Unique
    private Widget createTopRightButton() {
        MinecraftClient client = this.client;

        if (client == null) {
            BTWRMod.LOGGER.error("[{}] Can't create difficulty widget: Client is null", BTWRMod.MOD_NAME);
            return null;
        }


        CyclingButtonWidget<BTWRDifficulty> difficultyButton;
        LockButtonWidget lockButton;

        if (client.world != null && client.isIntegratedServerRunning()) {
            difficultyButton = createDifficultyButtonWidget(0, 0, "options.difficulty", this.client);
            if (!client.world.getLevelProperties().isHardcore()) {
                lockButton = new LockButtonWidget(
                        0, 0,
                        button -> {
                            assert this.client.world != null;
                            client
                                    .setScreen(
                                            new ConfirmScreen(
                                                    this::btwr$lockDifficulty,
                                                    Text.translatable("difficulty.lock.title"),
                                                    Text.translatable("difficulty.lock.question", ClientBTWRDifficultyCache.get().getTooltip())
                                            )
                                    );
                        });
                difficultyButton.setWidth(difficultyButton.getWidth() - lockButton.getWidth());
                lockButton.setLocked(client.world.getLevelProperties().isDifficultyLocked());
                lockButton.active = !lockButton.isLocked();
                difficultyButton.active = !lockButton.isLocked();
                AxisGridWidget axisGridWidget = new AxisGridWidget(150, 0, AxisGridWidget.DisplayAxis.HORIZONTAL);
                axisGridWidget.add(difficultyButton);
                axisGridWidget.add(lockButton);
                return axisGridWidget;
            } else {
                assert difficultyButton != null;
                difficultyButton.active = false;
                return difficultyButton;
            }
        } else {
            return ButtonWidget.builder(Text.translatable("options.online"), button -> client.setScreen(new OnlineOptionsScreen(this, this.settings)))
                    .dimensions(this.width / 2 + 5, this.height / 6 - 12 + 24, 150, 20)
                    .build();
        }
    }

    /**
    @Unique
    private static CyclingButtonWidget<BTWRDifficulty> createDifficultyButtonWidget(int x, int y, String translationKey, MinecraftClient client) {
        return CyclingButtonWidget.builder(BTWRDifficulty::getDisplayName)
                .values(DifficultyRegistry.getAll())
                .initially(ClientBTWRDifficultyCache.get())
                .build(
                        x, y, 150, 20,
                        Text.translatable(translationKey),
                        (button, difficulty) -> ClientPlayNetworking.send(
                                new UpdateBTWRDifficultyC2SPacket(difficulty.info())
                        )
                );
    }

    @Unique
    private void btwr$lockDifficulty(boolean difficultyLocked) {
        assert this.client != null;
        assert this.client.getNetworkHandler() != null;

        CyclingButtonWidget<BTWRDifficulty> difficultyButton = screenHelper.getDifficultyButton();
        LockButtonWidget lockButton = screenHelper.getLockButton();

        this.client.setScreen(this);
        if (difficultyLocked && this.client.world != null && lockButton != null && difficultyButton != null) {
            this.client.getNetworkHandler().sendPacket(new UpdateDifficultyLockC2SPacket(true));
            lockButton.setLocked(true);
            lockButton.active = false;
            difficultyButton.active = false;
        }
    }
    **/

}