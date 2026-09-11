package org.btwr.core.gui;

import net.minecraft.client.gui.widget.LockButtonWidget;
import org.jetbrains.annotations.Nullable;

/**
 * Builds the difficulty + lock button row shared by multiple screens.
 * NOT static: each screen (mixin instance) must create its own instance,
 * since the widgets built here belong to that one screen's layout and
 * can't be reused across screens.
 */
public class BTWRDifficultyScreenHelper {

    //private @Nullable CyclingButtonWidget<BTWRDifficulty> difficultyButton;
    private @Nullable LockButtonWidget lockButton;

    // Two independent reasons the lock can be forced on. Either being true
    // is enough to disable the lock button; clearing one must not undo the
    // other.
    private boolean forcedByDifficulty;
    private boolean forcedByCheatsDisabled;
    private boolean manuallyLocked;

    /**

    /**
     * @param initiallySelected  starting difficulty value
     * @param initiallyLocked    whether the row should start manually locked
     * @param onSelectionChanged called when the user picks a different difficulty
     * @param onLockConfirmed    called after the confirm dialog closes, with
     *                           (screenToReturnTo, confirmed)
     */

    /**
    public DirectionalLayoutWidget buildCreateWorld(
            BTWRDifficulty initiallySelected,
            boolean initiallyLocked,
            Consumer<BTWRDifficulty> onSelectionChanged,
            BiConsumer<Screen, Boolean> onLockConfirmed
    ) {
        DirectionalLayoutWidget row = DirectionalLayoutWidget.horizontal().spacing(4);

        BTWRDifficulty[] visibleDifficulties = DifficultyRegistry.getAll().stream()
                .filter(diff -> !ModDifficulties.getHidden().contains(diff))
                .toArray(BTWRDifficulty[]::new);

        difficultyButton = row.add(
                CyclingButtonWidget.builder(BTWRDifficulty::getDisplayName)
                        .values(visibleDifficulties)
                        .tooltip(value -> Tooltip.of(value.getTooltip()))
                        .initially(initiallySelected)
                        .build(
                                0, 0, 190, 20,
                                Text.translatable("difficulty.btwr"),
                                (btn, value) -> onSelectionChanged.accept(value)
                        )
        );

        lockButton = row.add(new LockButtonWidget(0, 0, btn -> {
            MinecraftClient client = MinecraftClient.getInstance();
            Screen current = client.currentScreen;
            if (current == null || difficultyButton == null) return;

            client.setScreen(new ConfirmScreen(
                    confirmed -> onLockConfirmed.accept(current, confirmed),
                    Text.translatable("difficulty.lock.title"),
                    Text.translatable("difficulty.lock.question", difficultyButton.getValue().getDisplayName())
            ));
        }));

        manuallyLocked = initiallyLocked;
        if (initiallyLocked) {
            applyManualLock(true);
        }

        return row;
    }

    /**
     * Player explicitly locked/unlocked via the confirm dialog. Locking this
     * way freezes both buttons — there's no UI path back to unlocked once
     * set, matching the original "confirming the lock is permanent for this
     * screen" behavior.
     */
    /**
    public void applyManualLock(boolean locked) {
        if (lockButton == null) return;
        if (difficultyButton == null) return;

        manuallyLocked = locked;

        if (locked) {
            lockButton.setLocked(true);
            lockButton.active = false;
            difficultyButton.active = false;
        }
    }

    public void setDifficultyForcesLock(boolean forced) {
        this.forcedByDifficulty = forced;
        recomputeForcedLock();
    }

    private void recomputeForcedLock() {
        if (lockButton == null) return;
        boolean forced = forcedByDifficulty || forcedByCheatsDisabled;
        if (forced) {
            lockButton.setLocked(true);
            lockButton.active = false;
        } else if (!manuallyLocked) {
            lockButton.setLocked(false);
            lockButton.active = true;
        }
    }

    public boolean isForced() {
        return forcedByDifficulty || forcedByCheatsDisabled;
    }

    public @Nullable CyclingButtonWidget<BTWRDifficulty> getDifficultyButton() {
        return difficultyButton;
    }

    public @Nullable LockButtonWidget getLockButton() {
        return lockButton;
    }
     **/
}