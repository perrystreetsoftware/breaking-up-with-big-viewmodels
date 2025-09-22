# Breaking Up with Big ViewModels

This is a sample Android app that demonstrates how to split long ViewModels into smaller ones, using the **Mediator pattern**, and tie them to smaller and distinct parts of the screen.

## What You'll Learn

- Break a large ViewModel into smaller, independently testable ViewModels, each tied to a smaller component of the screen.
- Enable the smaller, screen-scoped ViewModels to reactively communicate with each other through a shared Mediator.
- Use Hilt or Koin to scope the Mediator to the screen’s lifecycle, automatically disposing it when the user exits that screen.

## Architecture

### View Layer
- **AppNavHost** - Compose Navigation setup
- **GridScreen** - Main gallery screen displaying media items
  - **Grid** - Shows the grid of media items
  - **TopBar** - Shows selection count and overflow menu
  - **BottomBar** - Action buttons (favorite, share, star, delete)

### ViewModel Layer
- **GridViewModel** - Handles the grid state (the list of media items)
- **TopBarViewModel** - Manages top bar state (selection count)
- **BottomBarViewModel** - Controls bottom bar visibility and actions
- **GridMediator** - Holds the common UI state for the ViewModels to observe

### UseCase Layer
- **GetMediaUseCase** - Fetches media data from repository

### Repository Layer
- **MediaRepository** - Abstracts data access

### Data Source Layer
- **MediaDataSource** / **MediaDataSourceImpl** - Handles data fetching from assets

## 🎥 Check out our droidcon talk!

For more information, watch our droidcon talk [**"Breaking Up with Big ViewModels — Without Breaking Your Architecture"**](https://berlin.droidcon.com/speakers/stelios-frantzeskakis)
